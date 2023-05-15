package com.milestone2.app.data;
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

// import com.milestone1.app.models.Rating;

// @EnableBatchProcessing
// @Configuration
// public class RatingCsvToMongoJob {

//     @Autowired
//     private JobBuilderFactory jobBuilderFactory;
//     @Autowired
//     private StepBuilderFactory stepBuilderFactory;

//     @Autowired
//     private MongoTemplate mongoTemplate;

//     @Bean
//     public Job readRatingCSVFile() {
//         return jobBuilderFactory.get("readRatingCSVFile").incrementer(new RunIdIncrementer()).start(ratingStep())
//                 .build();
//     }

//     @Bean
//     public Step ratingStep() {
//         return stepBuilderFactory.get("ratingStep").<Rating, Rating>chunk(10).reader(ratingReader())
//                 .writer(ratingWriter()).build();
//     }

//     @Bean
//     public FlatFileItemReader<Rating> ratingReader() {
//         FlatFileItemReader<Rating> reader = new FlatFileItemReader<Rating>();
//         reader.setResource(new ClassPathResource("ml-1m/ratings.csv"));
//         reader.setLinesToSkip(1); // skip the header row
//         reader.setLineMapper(new DefaultLineMapper<Rating>() {{
//             setLineTokenizer(new DelimitedLineTokenizer() {{
//                 setNames("userId", "movieId", "rating", "timestamp");
//                 setDelimiter(","); // set the delimiter used in the CSV file
//             }});
//             setFieldSetMapper(new BeanWrapperFieldSetMapper<Rating>() {{
//                 setTargetType(Rating.class);
//             }});
//         }});
//         return reader;
//     }

//     @Bean
//     public MongoItemWriter<Rating> ratingWriter() {
//         MongoItemWriter<Rating> writer = new MongoItemWriter<>();
//         writer.setTemplate(mongoTemplate);
//         writer.setCollection("ratings");
//         return writer;
//     }
// }
