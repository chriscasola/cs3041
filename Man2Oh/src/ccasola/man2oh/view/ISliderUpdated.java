package ccasola.man2oh.view;

import javax.swing.JSlider;

/**
 * An interface to be implemented by all classes that need to be notified
 * when the position of the {@link HelpLevelSlider} changes.
 */
public interface ISliderUpdated {

	/**
	 * The method called when the help level slider changes
	 * @param slider the slider that was changed
	 */
	public void sliderChanged(JSlider slider);
}
