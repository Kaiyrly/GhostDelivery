// package com.milestone2.app.data;

// import com.milestone2.app.models.MenuItem;
// import org.springframework.batch.core.Job;
// import org.springframework.batch.core.Step;
// import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
// import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
// import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
// import org.springframework.batch.core.launch.support.RunIdIncrementer;
// import org.springframework.batch.item.data.MongoItemWriter;
// import org.springframework.batch.item.file.FlatFileItemReader;
// import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
// import org.springframework.batch.item.file.mapping.DefaultLineMapper;
// import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.io.ClassPathResource;
// import org.springframework.data.mongodb.core.MongoTemplate;

// @EnableBatchProcessing
// @Configuration
// public class MenuCsvToMongoJob {

//     private final JobBuilderFactory jobBuilderFactory;
//     private final StepBuilderFactory stepBuilderFactory;
//     private final MongoTemplate mongoTemplate;

//     @Autowired
//     public MenuCsvToMongoJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, MongoTemplate mongoTemplate) {
//         this.jobBuilderFactory = jobBuilderFactory;
//         this.stepBuilderFactory = stepBuilderFactory;
//         this.mongoTemplate = mongoTemplate;
//     }

//     @Bean
//     public Job uploadMenuDataJob() {
//         return jobBuilderFactory.get("uploadMenuDataJob")
//                 .incrementer(new RunIdIncrementer())
//                 .start(menuDataStep())
//                 .build();
//     }

//     @Bean
//     public Step menuDataStep() {
//         return stepBuilderFactory.get("menuDataStep")
//                 .<MenuItem, MenuItem>chunk(10)
//                 .reader(menuDataReader())
//                 .writer(menuDataWriter())
//                 .build();
//     }

//     @Bean
//     public FlatFileItemReader<MenuItem> menuDataReader() {
//         FlatFileItemReader<MenuItem> reader = new FlatFileItemReader<>();
//         reader.setResource(new ClassPathResource("ml-1m/menu.csv")); // Update with the correct path to your CSV file
//         reader.setLinesToSkip(1); // Skip the header row
//         reader.setLineMapper(new DefaultLineMapper<MenuItem>() {{
//             setLineTokenizer(new DelimitedLineTokenizer() {{
//                 setNames("restaurant", "item", "price"); // Update with the column names in your CSV file
//                 setDelimiter(","); // Set the delimiter used in the CSV file
//             }});
//             setFieldSetMapper(new BeanWrapperFieldSetMapper<MenuItem>() {{
//                 setTargetType(MenuItem.class);
//             }});
//         }});
//         return reader;
//     }

//     @Bean
//     public MongoItemWriter<MenuItem> menuDataWriter() {
//         MongoItemWriter<MenuItem> writer = new MongoItemWriter<>();
//         writer.setTemplate(mongoTemplate);
//         writer.setCollection("menuItems"); // Set the collection name where you want to store the menu items
//         return writer;
//     }
// }
