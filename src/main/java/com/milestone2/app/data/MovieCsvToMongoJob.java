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

// import com.milestone1.app.models.Movie;
// @EnableBatchProcessing
// @Configuration
// public class MovieCsvToMongoJob {

//     @Autowired
//     private JobBuilderFactory jobBuilderFactory;
//     @Autowired
//     private StepBuilderFactory stepBuilderFactory;

//     @Autowired
//     private MongoTemplate mongoTemplate;

//     @Bean
//     public Job readMovieCSVFile() {
//         return jobBuilderFactory.get("readMovieCSVFile").incrementer(new RunIdIncrementer()).start(movieStep())
//                 .build();
//     }

//     @Bean
//     public Step movieStep() {
//         return stepBuilderFactory.get("movieStep").<Movie, Movie>chunk(10).reader(movieReader())
//                 .writer(movieWriter()).build();
//     }

//     @Bean
//     public FlatFileItemReader<Movie> movieReader() {
//         FlatFileItemReader<Movie> reader = new FlatFileItemReader<>();
//         reader.setResource(new ClassPathResource("ml-1m/movies.csv"));
//         reader.setLinesToSkip(1); // skip the header row
//         reader.setLineMapper(new DefaultLineMapper<Movie>() {{
//             setLineTokenizer(new DelimitedLineTokenizer() {{
//                 setNames("movieId", "title", "genres");
//                 setDelimiter(","); // set the delimiter used in the CSV file
//             }});
//             setFieldSetMapper(new BeanWrapperFieldSetMapper<Movie>() {{
//                 setTargetType(Movie.class);
//             }});
//         }});
//         return reader;
//     }

//     @Bean
//     public MongoItemWriter<Movie> movieWriter() {
//         MongoItemWriter<Movie> writer = new MongoItemWriter<>();
//         writer.setTemplate(mongoTemplate);
//         writer.setCollection("movies");
//         return writer;
//     }
// }