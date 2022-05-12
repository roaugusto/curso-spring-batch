package br.com.digos.springbatch.jobs;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class EvenOrOdd {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Bean
  public Job printEvenOrOddJob() {
    return jobBuilderFactory
        .get("printEvenOrOddJob")
        .start(printEvenOrOddStep())
        .incrementer(new RunIdIncrementer())
        .build();
  }

  public Step printEvenOrOddStep() {
    return stepBuilderFactory
        .get("printEvenOrOddStep")
        .<Integer, String>chunk(1)
        .reader(countUntil10Reader())
        .processor(evenOrOddProcessor())
        .writer(printWriter())
        .build();
  }

  private IteratorItemReader<Integer> countUntil10Reader() {
    List<Integer> numbersOneUntilTen = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    return new IteratorItemReader<Integer>(numbersOneUntilTen.iterator());
  }

  private FunctionItemProcessor<Integer, String> evenOrOddProcessor() {
    return new FunctionItemProcessor<Integer, String>(
        item -> item % 2 == 0 ? String.format("Item %s é Par", item) : String.format("Item %s é Ímpar", item));
  }

  private ItemWriter<String> printWriter() {
    return items -> items.forEach(System.out::println);
  }

}
