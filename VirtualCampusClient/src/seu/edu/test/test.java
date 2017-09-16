package seu.edu.test;

import seu.edu.client.srv.UserClientSrv;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserClientSrv ucs = new UserClientSrv();
		
//		System.out.println(ucs.register("424", "425", "425", 1));// 1
//		System.out.println(ucs.register("424", "425", "425", 1));// 0
//		System.out.println(ucs.register("123", "123", "234", 1));// -1
//		System.out.println(ucs.register("4", "425", "425", 1));// 2
//		System.out.println(ucs.login("424", "425", 1));// 2
//		System.out.println(ucs.login("424", "424", 1));// 1
//		System.out.println(ucs.login("422", "425", 1));// 0
		System.out.println(ucs.resetPassword("424", "425", "424"));// 1
		System.out.println(ucs.resetPassword("424", "425", "424"));// 0
	}

}
