package model.impl;

import model.AbstractLineType;

public class ServiceTypeImpl extends AbstractLineType {

    public ServiceTypeImpl(String serviceType) {
        super(serviceType);
    }

    public String getServiceId() {
        return getBaseId();
    }

    public String getVariation() {
        return getVariationId();
    }

    public boolean isSpecial() {
        return getSpecialType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ServiceTypeImpl serviceType = (ServiceTypeImpl) o;
        return serviceType.getServiceId().equals(this.getServiceId())
                && (serviceType.getVariation() == null && this.getVariation() == null
                || serviceType.getVariation().equals(this.getVariation()))
                && serviceType.isSpecial() == this.isSpecial();
    }
}

