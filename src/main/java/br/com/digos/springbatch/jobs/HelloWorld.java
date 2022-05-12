// package br.com.digos.springbatch.jobs;

// import org.springframework.batch.core.Job;
// import org.springframework.batch.core.Step;
// import org.springframework.batch.core.StepContribution;
// import
// org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
// import
// org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
// import
// org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
// import org.springframework.batch.core.configuration.annotation.StepScope;
// import org.springframework.batch.core.launch.support.RunIdIncrementer;
// import org.springframework.batch.core.scope.context.ChunkContext;
// import org.springframework.batch.core.step.tasklet.Tasklet;
// import org.springframework.batch.repeat.RepeatStatus;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @EnableBatchProcessing
// @Configuration
// public class HelloWorld {

// @Autowired
// private JobBuilderFactory jobBuilderFactory;

// @Autowired
// private StepBuilderFactory stepBuilderFactory;

// @Bean
// public Job printHelloJob() {
// return jobBuilderFactory
// .get("printHelloJob")
// .start(printHelloStep())
// .incrementer(new RunIdIncrementer())
// .build();
// }

// public Step printHelloStep() {
// return stepBuilderFactory
// .get("printHelloStep")
// .tasklet(printHelloTasklet(null))
// .build();

// }

// @Bean
// @StepScope
// public Tasklet printHelloTasklet(@Value("#{jobParameters['name']}") String
// name) {
// return new Tasklet() {

// @Override
// public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws
// Exception {
// System.out.println(String.format("Hello %s!", name));
// return RepeatStatus.FINISHED;
// }

// };
// }

// }
