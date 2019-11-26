package com.example.NoteApp.util;


public class IdWorkerManage {

	private static IdWorker idWorkerInstance = null;


	/**
	 * single 单例模式
	 * @return
	 */
	private static synchronized IdWorker getIdWorkerInstance() {
		if (idWorkerInstance == null) {
			idWorkerInstance = new IdWorker(5, 5);
		}
		return idWorkerInstance;
	}

	public static long getId() {
		return getIdWorkerInstance().getId();
	}

}
