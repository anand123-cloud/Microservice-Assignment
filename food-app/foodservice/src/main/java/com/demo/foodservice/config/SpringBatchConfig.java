package com.demo.foodservice.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.demo.foodservice.dto.MenuItemDto;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory,
			StepBuilderFactory stepBuilderFactory,
			ItemReader<MenuItemDto> itemReader,
			ItemProcessor<MenuItemDto, MenuItemDto> itemProcessor,
			ItemWriter<MenuItemDto> itemWriter) {
		
		Step step=stepBuilderFactory.get("ETL-file-load")
				.<MenuItemDto,MenuItemDto>chunk(100)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build();
		
		return jobBuilderFactory.get("ETL-LOAD")
		.incrementer(new RunIdIncrementer())
		.start(step)
		.build();
		
	}
	
	@Bean
	public FlatFileItemReader<MenuItemDto> fileItemReader(){
		FlatFileItemReader<MenuItemDto> flatFileItemReader=new FlatFileItemReader<>();
	    Resource resource = resourceLoader.getResource("classpath:vendor.csv");
		flatFileItemReader.setResource(resource);
		flatFileItemReader.setName("CSV-READER");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;
		
	}

	@Bean
	public  LineMapper<MenuItemDto> lineMapper() {
        DefaultLineMapper<MenuItemDto> defaultLineMapper=new DefaultLineMapper<>();
        
        DelimitedLineTokenizer delimitedLineTokenizer=new DelimitedLineTokenizer();
        delimitedLineTokenizer.setStrict(false);
        delimitedLineTokenizer.setDelimiter(",");
        delimitedLineTokenizer.setNames(new String[] {"vendorid","name","desc","price","type"});
        
        BeanWrapperFieldSetMapper<MenuItemDto> fieldSetMapper=new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(MenuItemDto.class);
        
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		return defaultLineMapper;
	}

}
