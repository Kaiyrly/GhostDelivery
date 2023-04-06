// package com.milestone1.app.data;

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

// import com.milestone1.app.models.User;


// @EnableBatchProcessing
// @Configuration
// public class UserCsvToMongoJob {

//     @Autowired
//     private JobBuilderFactory jobBuilderFactory;
//     @Autowired
//     private StepBuilderFactory stepBuilderFactory;

//     @Autowired
//     private MongoTemplate mongoTemplate;



//     @Bean
//     public Job readUserCSVFile() {
//         return jobBuilderFactory.get("readUserCSVFile").incrementer(new RunIdIncrementer()).start(userStep())
//                 .build();
//     }

//     @Bean
//     public Step userStep() {
//         return stepBuilderFactory.get("userStep").<User, User>chunk(10).reader(userReader())
//                 .writer(userWriter()).build();
//     }

//     @Bean
//     public FlatFileItemReader<User> userReader() {
//         FlatFileItemReader<User> reader = new FlatFileItemReader<>();
//         reader.setResource(new ClassPathResource("ml-1m/users.csv"));
//         reader.setLinesToSkip(1); // skip the header row
//         reader.setLineMapper(new DefaultLineMapper<User>() {{
//             setLineTokenizer(new DelimitedLineTokenizer() {{
//                 setNames("userId", "gender", "age", "occupation", "zipCode");
//                 setDelimiter(","); // set the delimiter used in the CSV file
//             }});
//             setFieldSetMapper(new BeanWrapperFieldSetMapper<User>() {{
//                 setTargetType(User.class);
//             }});
//         }});
//         return reader;
//     }

//     @Bean
//     public MongoItemWriter<User> userWriter() {
//         MongoItemWriter<User> writer = new MongoItemWriter<>();
//         writer.setTemplate(mongoTemplate);
//         writer.setCollection("users");
//         return writer;
//     }
// }