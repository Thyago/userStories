package com.voluptor.component.archivable.model;

import com.voluptor.component.archivable.exception.ArchivedException;
import com.voluptor.component.archivable.exception.UnarchivedException;

public interface Archivable {
	
	public void setIsArchived(Boolean isArchived); 
	
	public Boolean getIsArchived();
	
	public default void archive() throws ArchivedException
	{
		if (getIsArchived() == true) {
			throw new ArchivedException();
		}
		this.setIsArchived(true);
	}
	
	public default void unarchive() throws UnarchivedException
	{
		if (getIsArchived() != true) {
			throw new UnarchivedException();
		}
		
		this.setIsArchived(false);
	}
}
