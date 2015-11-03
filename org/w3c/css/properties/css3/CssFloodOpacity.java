// $Id$
// Author: Yves Lafon <ylafon@w3.org>
//
// (c) COPYRIGHT MIT, ERCIM, Keio, Beihang, 2015.
// Please first read the full copyright statement in file COPYRIGHT.html
package org.w3c.css.properties.css3;

import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * @spec http://www.w3.org/TR/2014/WD-filter-effects-1-20141125/#FloodOpacityProperty
 */
public class CssFloodOpacity extends org.w3c.css.properties.css.CssFloodOpacity {

	/**
	 * Create a new CssFloodOpacity
	 */
	public CssFloodOpacity() {
		value = initial;
	}

	/**
	 * Creates a new CssFloodOpacity
	 *
	 * @param expression The expression for this property
	 * @throws org.w3c.css.util.InvalidParamException
	 *          Expressions are incorrect
	 */
	public CssFloodOpacity(ApplContext ac, CssExpression expression, boolean check)
			throws InvalidParamException {
		if (check && expression.getCount() > 1) {
			throw new InvalidParamException("unrecognize", ac);
		}

		setByUser();
		CssValue val = expression.getValue();

		switch (val.getType()) {
			case CssTypes.CSS_NUMBER:
			case CssTypes.CSS_PERCENTAGE:
				value = val;
				break;
			default:
				throw new InvalidParamException("value",
						val.toString(),
						getPropertyName(), ac);
		}
	}

	public CssFloodOpacity(ApplContext ac, CssExpression expression)
			throws InvalidParamException {
		this(ac, expression, false);
	}

}

