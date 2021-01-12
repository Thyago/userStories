package com.voluptor.component.archivable.service;

import com.voluptor.component.archivable.exception.ArchivedException;
import com.voluptor.component.archivable.exception.UnarchivedException;

//TODO: Check how to force T to be Archivable, forcing it to use an object that implements archivable
public interface ArchivableService<T> {
	
	public void archive(T archivableObject) throws ArchivedException;
	
	public void unarchive(T archivableObject) throws UnarchivedException;
}
