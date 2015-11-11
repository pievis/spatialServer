package it.isac.server.testing;

public class TestMain {
	
	final static String TEST1 = "test1";
	final static String TEST2 = "test2";
	final static String TEST_MULTI1 = "multi1";
	
	public static void main(String args[]){
		String[] prms = {TEST1, TEST2, TEST_MULTI1};
		if(args.length == 0)
		{
			System.out.println("Usare i seguenti parametri per lanciare i test:");
			for(String s : prms)
				System.out.println(s);
			return;
		}
		if(args[0].equals(TEST1))
			new ClientTest1().main(args);
		if(args[0].equals(TEST2))
			new ClientTest2().main(args);
		if(args[0].equals(TEST_MULTI1))
			new MultiClientTest().main(args);
	}
}
