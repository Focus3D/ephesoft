/********************************************************************************* 
* Ephesoft is a Intelligent Document Capture and Mailroom Automation program 
* developed by Ephesoft, Inc. Copyright (C) 2010-2012 Ephesoft Inc. 
* 
* This program is free software; you can redistribute it and/or modify it under 
* the terms of the GNU Affero General Public License version 3 as published by the 
* Free Software Foundation with the addition of the following permission added 
* to Section 15 as permitted in Section 7(a): FOR ANY PART OF THE COVERED WORK 
* IN WHICH THE COPYRIGHT IS OWNED BY EPHESOFT, EPHESOFT DISCLAIMS THE WARRANTY 
* OF NON INFRINGEMENT OF THIRD PARTY RIGHTS. 
* 
* This program is distributed in the hope that it will be useful, but WITHOUT 
* ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS 
* FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more 
* details. 
* 
* You should have received a copy of the GNU Affero General Public License along with 
* this program; if not, see http://www.gnu.org/licenses or write to the Free 
* Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 
* 02110-1301 USA. 
* 
* You can contact Ephesoft, Inc. headquarters at 111 Academy Way, 
* Irvine, CA 92617, USA. or at email address info@ephesoft.com. 
* 
* The interactive user interfaces in modified source and object code versions 
* of this program must display Appropriate Legal Notices, as required under 
* Section 5 of the GNU Affero General Public License version 3. 
* 
* In accordance with Section 7(b) of the GNU Affero General Public License version 3, 
* these Appropriate Legal Notices must retain the display of the "Ephesoft" logo. 
* If the display of the logo is not reasonably feasible for 
* technical reasons, the Appropriate Legal Notices must display the words 
* "Powered by Ephesoft". 
********************************************************************************/ 

package com.ephesoft.dcma.gwt.admin.bm.client.view.tablecolumninfo.advancedtableextraction;

/**
 * This class provides functionality to handle coordinates.
 * 
 * @author Ephesoft
 * @version 1.0
 */
public class Coordinates {

	/**
	 * xCoordinate0 int.
	 */
	private int xCoordinate0;

	/**
	 * yCoordinate0 int.
	 */
	private int yCoordinate0;

	/**
	 * xCoordinate1 int.
	 */
	private int xCoordinate1;

	/**
	 * yCoordinate1 int.
	 */
	private int yCoordinate1;

	/**
	 * advancedTableExtractionView AdvancedTableExtractionView.
	 */
	private AdvancedTableExtractionView advancedTableExtractionView;

	/**
	 * CROSSHAIR_REDUCTION_FACTOR int.
	 */
	private static final int CROSSHAIR_REDUCTION_FACTOR = 5;

	/**
	 * Constructor.
	 * 
	 * @param advancedTableExtractionView AdvancedTableExtractionView
	 */
	public Coordinates(AdvancedTableExtractionView advancedTableExtractionView) {
		this.advancedTableExtractionView = advancedTableExtractionView;
	}

	/**
	 * Constructor.
	 */
	public Coordinates() {
		super();
	}

	/**
	 * To set Initial Coordinates.
	 * 
	 * @param xCoordinate0 int
	 * @param yCoordinate0 int
	 */
	public void setInitialCoordinates(int xCoordinate0, int yCoordinate0) {
		this.xCoordinate0 = xCoordinate0;
		this.yCoordinate0 = yCoordinate0;
	}

	/**
	 * To set Other Coordinates.
	 * 
	 * @param xCoordinate1 int
	 * @param yCoordinate1 int
	 * @param isFinal boolean
	 */
	public void setOtherCoordinates(int xCoordinate1, int yCoordinate1, boolean isFinal) {
		this.xCoordinate1 = xCoordinate1;
		this.yCoordinate1 = yCoordinate1;
		if (isFinal) {
			setCoordinates(getQuadrantValues());
		}
	}

	/**
	 * To get Quadrant Values.
	 * 
	 * @return Coordinates
	 */
	public Coordinates getQuadrantValues() {
		Coordinates returnVal;
		int initialX = this.xCoordinate0;
		int initialY = this.yCoordinate0;
		int finalX = this.xCoordinate1;
		int finalY = this.yCoordinate1;
		if (initialX <= finalX && initialY <= finalY) {
			// it is in the III quadrant.
			returnVal = getCoordinates(initialX, initialY, finalX, finalY);
		} else if (initialX >= finalX && initialY <= finalY) {
			// it is in IV quadrant.
			returnVal = getCoordinates(finalX, initialY, initialX, finalY);
		} else if (initialX <= finalX && initialY >= finalY) {
			// it is in II quadrant.
			returnVal = getCoordinates(initialX, finalY, finalX, initialY);
		} else if (initialX >= finalX && initialY >= finalY) {
			// it is in I quadrant.
			returnVal = getCoordinates(finalX + CROSSHAIR_REDUCTION_FACTOR, finalY + CROSSHAIR_REDUCTION_FACTOR, initialX, initialY);
		} else {
			returnVal = getCoordinates(initialX, initialY, finalX, finalY);
		}
		return returnVal;
	}

	private Coordinates getCoordinates(int initialX, int initialY, int finalX, int finalY) {
		Coordinates coordinates = new Coordinates();
		coordinates.set(initialX, initialY, finalX, finalY);
		return coordinates;
	}

	/**
	 * To set coordinates.
	 * 
	 * @param initialX int
	 * @param initialY int
	 * @param finalX int
	 * @param finalY int
	 */
	public void set(int initialX, int initialY, int finalX, int finalY) {
		this.xCoordinate0 = initialX;
		this.xCoordinate1 = finalX;
		this.yCoordinate0 = initialY;
		this.yCoordinate1 = finalY;
	}

	/**
	 * To do overlay.
	 * 
	 * @param columnFinalized boolean
	 */
	public void doOverlay(final boolean columnFinalized) {
		this.advancedTableExtractionView.removeOverlay();
		Coordinates coordinates = this.getQuadrantValues();
		this.advancedTableExtractionView.createOverlay(coordinates.getX0(), coordinates.getX1(), coordinates.getY0(), coordinates
				.getY1(), 1, columnFinalized);
	}

	/**
	 * To get X0.
	 * 
	 * @return int
	 */
	public int getX0() {
		return xCoordinate0;
	}

	/**
	 * To set X0.
	 * 
	 * @param xCoordinate0 int
	 */
	public void setX0(int xCoordinate0) {
		this.xCoordinate0 = xCoordinate0;
	}

	/**
	 * To get Y0.
	 * 
	 * @return int
	 */
	public int getY0() {
		return yCoordinate0;
	}

	/**
	 * To set Y0.
	 * 
	 * @param yCoordinate0 int
	 */
	public void setY0(int yCoordinate0) {
		this.yCoordinate0 = yCoordinate0;
	}

	/**
	 * To get X1.
	 * 
	 * @return int
	 */
	public int getX1() {
		return xCoordinate1;
	}

	/**
	 * To set X1.
	 * 
	 * @param xCoordinate1 int
	 */
	public void setX1(int xCoordinate1) {
		this.xCoordinate1 = xCoordinate1;
	}

	/**
	 * To get Y1.
	 * 
	 * @return int
	 */
	public int getY1() {
		return yCoordinate1;
	}

	/**
	 * To set Y1.
	 * 
	 * @param yCoordinate1 int
	 */
	public void setY1(int yCoordinate1) {
		this.yCoordinate1 = yCoordinate1;
	}

	/**
	 * To set Coordinates.
	 * 
	 * @param valueCoordinates Coordinates
	 */
	public void setCoordinates(Coordinates valueCoordinates) {
		this.xCoordinate0 = valueCoordinates.getX0();
		this.xCoordinate1 = valueCoordinates.getX1();
		this.yCoordinate0 = valueCoordinates.getY0();
		this.yCoordinate1 = valueCoordinates.getY1();
	}

	/**
	 * To clear all coordinates.
	 */
	public void clear() {
		this.xCoordinate0 = 0;
		this.xCoordinate1 = 0;
		this.yCoordinate0 = 0;
		this.yCoordinate1 = 0;
	}

	/**
	 * To check whether all coordinates are zero.
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		boolean returnVal = false;
		if (this.xCoordinate0 == 0 && this.xCoordinate1 == 0 && this.yCoordinate0 == 0 && this.yCoordinate1 == 0) {
			returnVal = true;
		}
		return returnVal;
	}

	/**
	 * To set Column Coordinates.
	 */
	public void setColumnCoordinates() {
		this.advancedTableExtractionView.setColCoordinates(String.valueOf(xCoordinate0), String.valueOf(xCoordinate1), String
				.valueOf(yCoordinate0), String.valueOf(yCoordinate1));
	}

}
