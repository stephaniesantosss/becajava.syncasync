package Controller;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import Service.Sync;

public class Main {
	
//  ExecutorService == Classe para o gerenciamento de execuções em paralelo

//  threadpool == É um conjunto de threads (sequências) pré-instanciadas prontas para uso.
//	Elas não costumam ser liberadas, e ficam lá disponíveis para reciclagem.

	private static final ExecutorService threadpool = Executors.newFixedThreadPool(3);

	public static void main(String args[]) throws InterruptedException, ExecutionException {
		GerarNumeroAleatorio gerarnum = new GerarNumeroAleatorio();
		System.out.println("Exemplo Async: Gerador de número\n");
		System.out.println("Processando a tarefa ...");
		Future<Integer> future = threadpool.submit(gerarnum);
		while (!future.isDone()) {
			System.out.println("A tarefa ainda não foi processada!");
			Thread.sleep(1); // dormir por 1 milissegundo

		}
		System.out.println("Tarefa completa!");
		long num = (long) future.get();
		System.out.println("O número gerado foi: " + num+ "\n\n\n\n\n\n\n");
		threadpool.shutdown();
	
		System.out.println("Exemplo Sync: imprimir Sync\n");
		Sync sync = new Sync();
		sync.foo();
		sync.bar();
		sync.baz();
	
	}

	// Classe que implementa a interface Callable e retorna um numero aleatorio
	private static class GerarNumeroAleatorio implements Callable<Integer> {

		@Override
		public Integer call() {
			Random random = new Random();
			Integer numero = random.nextInt(100);
			return numero;
		}

	}
	
	

}
