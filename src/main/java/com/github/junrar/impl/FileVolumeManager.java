package com.github.junrar.impl;

import java.io.File;
import java.io.IOException;

import com.github.junrar.Archive;
import com.github.junrar.Volume;
import com.github.junrar.VolumeManager;
import com.github.junrar.util.VolumeHelper;


/**
 * @author <a href="http://www.rogiel.com">Rogiel</a>
 * 
 */
public class FileVolumeManager implements VolumeManager {
	private final File firstVolume;

	public FileVolumeManager(File firstVolume) {
		this.firstVolume = firstVolume;
	}

	@Override
	public Volume nextArchive(Archive archive, Volume last)
			throws IOException {
		if (last == null)
			return new FileVolume(archive, firstVolume);

		FileVolume lastFileVolume = (FileVolume) last;
		boolean oldNumbering = !archive.getMainHeader().isNewNumbering()
				|| archive.isOldFormat();
		String nextName = VolumeHelper.nextVolumeName(lastFileVolume.getFile()
				.getAbsolutePath(), oldNumbering);
		File nextVolume = new File(nextName);

		return new FileVolume(archive, nextVolume);
	}
}
