//
// $Id$
// From Sijtsche de Jong (sy.de.jong@let.rug.nl)
//
// (c) COPYRIGHT 1995-2000  World Wide Web Consortium (MIT, INRIA, Keio University)
// Please first read the full copyright statement at
// http://www.w3.org/Consortium/Legal/copyright-software-19980720

package org.w3c.css.properties.css3;

import org.w3c.css.parser.CssStyle;
import org.w3c.css.properties.css1.CssProperty;
import org.w3c.css.util.ApplContext;
import org.w3c.css.util.InvalidParamException;
import org.w3c.css.values.CssExpression;
import org.w3c.css.values.CssIdent;
import org.w3c.css.values.CssLength;
import org.w3c.css.values.CssNumber;
import org.w3c.css.values.CssTypes;
import org.w3c.css.values.CssValue;

/**
 * http://www.w3.org/TR/css3-multicol/
 *  <P>
 *  <EM>Value:</EM> auto | always | avoid | left | right | page | column | 
 *                  avoid-page | avoid-column <BR>
 *  <EM>Initial:</EM>auto<BR>
 *  <EM>Applies to:</EM>block-level elements<BR>
 *  <EM>Inherited:</EM>no<BR>
 *  <EM>Percentages:</EM>N/A<BR>
 *  <EM>Media:</EM>:paged
 *  <P>
 * When content is laid out in multiple columns, the user agent must 
 * determine where column breaks are placed. The problem of breaking content
 * into columns is similar to breaking content into pages.
 * Three new properties are introduced to allow column breaks to be described 
 * in the same properties as page breaks: ‘break-before’, ‘break-after’, 
 * and ‘break-inside’. These properties take the same values as 
 * ‘page-break-before’, ‘page-break-after’, and ‘page-break-inside’ [CSS21]. 
 * In addition, some new keyword values are added.
 */

public class CssBreakAfter extends CssProperty {

    CssValue value;

    /**
     * Create a new CssColumnWidth
     */
    public CssBreakAfter() {
	value = CssBreakBefore.default_value;
    }

    /**
     * Create a new CssBreakAfter
     *
     * @param expression The expression for this property
     * @exception InvalidParamException Incorrect value
     */
    public CssBreakAfter(ApplContext ac, CssExpression expression,
	    boolean check) throws InvalidParamException {

	setByUser();
	CssValue val = expression.getValue();

	if(check && expression.getCount() > 1) {
	    throw new InvalidParamException("unrecognize", ac);
	}
	
	if ((val.getType() != CssTypes.CSS_IDENT) ||
	    !(((CssIdent)val).equals(inherit) || 
	      CssBreakBefore.acceptable_values.contains((CssIdent)val))) {
	    throw new InvalidParamException("value",
					    expression.getValue(),
					    getPropertyName(), ac);
	}
	value = CssIdent.getIdent(((CssIdent)val).toString());
	expression.next();
    }

    public CssBreakAfter(ApplContext ac, CssExpression expression)
	    throws InvalidParamException {
	this(ac, expression, false);
    }

    /**
     * Add this property to the CssStyle
     *
     * @param style The CssStyle
     */
    public void addToStyle(ApplContext ac, CssStyle style) {
	if (((Css3Style) style).cssBreakAfter != null)
	    style.addRedefinitionWarning(ac, this);
	((Css3Style) style).cssBreakAfter = this;
    }

    /**
     * Get this property in the style.
     *
     * @param style The style where the property is
     * @param resolve if true, resolve the style to find this property
     */
    public CssProperty getPropertyInStyle(CssStyle style, boolean resolve) {
	if (resolve) {
	    return ((Css3Style) style).getBreakAfter();
	}
	else {
	    return ((Css3Style) style).cssBreakAfter;
	}
    }

    /**
     * Compares two properties for equality.
     *
     * @param value The other property.
     */
    public boolean equals(CssProperty property) {
	return (property instanceof CssBreakAfter &&
		value.equals(((CssBreakAfter) property).value));
    }

    /**
     * Returns the name of this property
     */
    public String getPropertyName() {
	return "break-after";
    }

    /**
     * Returns the value of this property
     */
    public Object get() {
	return value;
    }

    /**
     * Returns true if this property is "softly" inherited
     */
    public boolean isSoftlyInherited() {
	return inherit.equals(value);
    }

    /**
     * Returns a string representation of the object
     */
    public String toString() {
	return value.toString();
    }

    /**
     * Is the value of this property a default value
     * It is used by alle macro for the function <code>print</code>
     */
    public boolean isDefault() {
	return (value == CssBreakBefore.default_value);
    }

}