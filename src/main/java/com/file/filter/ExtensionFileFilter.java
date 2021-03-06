package com.file.filter;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * Classe para filtrar os tipos de arqvuivo v�lidos
 * 
 * @author andrevaladas
 */
public class ExtensionFileFilter extends FileFilter {
	String description;

	String extensions[];

	public ExtensionFileFilter(final String description, final String extension) {
		this(description, new String[] { extension });
	}

	public ExtensionFileFilter(final String description, final String extensions[]) {
		if (description == null) {
			this.description = extensions[0];
		} else {
			this.description = description;
		}
		this.extensions = extensions.clone();
		toLower(this.extensions);
	}

	private void toLower(final String array[]) {
		for (int i = 0, n = array.length; i < n; i++) {
			array[i] = array[i].toLowerCase();
		}
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public boolean accept(final File file) {
		if (file.isDirectory()) {
			return true;
		} else {
			final String path = file.getAbsolutePath().toLowerCase();
			for (int i = 0, n = extensions.length; i < n; i++) {
				final String extension = extensions[i];
				if ((path.endsWith(extension) && (path.charAt(path.length() - extension.length() - 1)) == '.')) {
					return true;
				}
			}
		}
		return false;
	}
}