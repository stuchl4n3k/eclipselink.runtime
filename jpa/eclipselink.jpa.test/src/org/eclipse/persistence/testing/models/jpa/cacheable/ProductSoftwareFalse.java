/*******************************************************************************
 * Copyright (c) 2014, 2015  Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Tomas Kraus - Initial API and implementation
 ******************************************************************************/
package org.eclipse.persistence.testing.models.jpa.cacheable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * jUnit test data model to verify <code>@Cacheable</code> annotation functionality.
 * This class extends {@link ProductFalse} with <code>@Cacheable(false)</code>
 * and inherits <code>@Cacheable</code> settings from parent class.
 * Instances of this class are expected to not be in the cache.
 */
@Entity
@DiscriminatorValue("SW")
public class ProductSoftwareFalse extends ProductFalse {

    // Some more attribute to have there.
    /** Version number. */
    @Column(name = "VNUMBER")
    private int versionNumber;

    /**
     * Constructs an instance of hardware product class with caching turned off.
     */
    public ProductSoftwareFalse() {
        super();
    }

    /**
     * Constructs an instance of hardware product class with caching turned off.
     * @param id Product ID.
     * @param quantity Product quantity.
     * @param versionNumber Hardware product version number.
     */
    public ProductSoftwareFalse(int id, int quantity, int versionNumber) {
        super(id, quantity);
        this.versionNumber = versionNumber;
    }

    /**
     * Get hardware product version number.
     * @return Hardware product version number.
     */
    public int getVersionNumber() {
        return versionNumber;
    }

    /**
     * Set hardware product version number.
     * @param versionNumber Hardware product version number.
     */
    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    /**
     * Return {@link String} representation of this object in human readable form.
     * @return {@link String} representation of this object.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProductHardwareFalse: [");
        sb.append("id: ").append(getId()).append(", ");
        sb.append("quantity: ").append(getQuantity()).append(", ");
        sb.append("versionNumber: ").append(versionNumber).append("]");
        return sb.toString();
    }

}
