package org.windbell.lab.hamster.watch;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatchInstall {
	public static void main(String[]args) throws IOException{
		WatchService watcher=FileSystems.getDefault().newWatchService();
	}
	private void register(Path dir,WatchService wahcehr) throws IOException{
		WatchKey key = dir.register(wahcehr, StandardWatchEventKinds.ENTRY_CREATE,StandardWatchEventKinds.ENTRY_DELETE,StandardWatchEventKinds.ENTRY_MODIFY,StandardWatchEventKinds.OVERFLOW);
	}

}
