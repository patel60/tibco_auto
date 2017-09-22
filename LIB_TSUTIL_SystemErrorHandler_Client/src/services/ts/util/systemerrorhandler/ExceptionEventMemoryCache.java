package services.ts.util.systemerrorhandler;

import java.util.Vector;

public class ExceptionEventMemoryCache {

	private static String[] empty_string_array = new String[0];
	private static Vector<String> m_cache;

	public ExceptionEventMemoryCache() {
		super();
	}

	public synchronized boolean put(String p_logevent_xml, int p_m_size) {
		if(m_cache == null)
			m_cache = new Vector<String>(p_m_size);
		if(m_cache.size() >= p_m_size)
			return false;
		m_cache.add(p_logevent_xml);
		return true;

	}

	public synchronized String remove() {
		return (m_cache != null && m_cache.size() > 0) ? m_cache.remove(0) : null;
	}

	public synchronized String[] removeAll() {
		if (m_cache.size() > 0) {
			String[] local_m_cache = m_cache.toArray(empty_string_array);
			m_cache.removeAllElements();
			return local_m_cache;
		} else
			return null;
	}

	public synchronized String[] removeSubset(int p_subset_size) {
		
		if (m_cache != null){
		int p_local_subset_size = m_cache.size() >= p_subset_size ? p_subset_size
				: m_cache.size();

		if (p_local_subset_size > 0) {
			String[] local_m_cache = m_cache.subList(0, p_local_subset_size)
					.toArray(new String[0]);
			m_cache.subList(0, p_local_subset_size).clear();
			return local_m_cache;
		} else
			// When subset size <= to 0 removeAll()
			return removeAll();
		}
		return null;

	}
}
