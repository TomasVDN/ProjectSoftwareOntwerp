package canvaswindow;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;

/**
 * Singleton type class that can be called by all GUIElements to get the fontMetrics.
 */
public final class FontMetricGetter {
	
	private static final FontMetricGetter INSTANCE = new FontMetricGetter();

	private FontMetricGetter() {}

	/**
	 * Returns the instance of this singleton.
	 * @return this.instance
	 */
	public static FontMetricGetter getInstance() {
	        return INSTANCE;
	}
	
	/**
	 * Returns the metric corresponding to the given font.
	 * @param font - font to get the metric from
	 * @return metric corresponding to the given font
	 */
	@SuppressWarnings("deprecation")
	public FontMetrics getFontMetric(Font font){
		return Toolkit.getDefaultToolkit().getFontMetrics(font);
	}
}