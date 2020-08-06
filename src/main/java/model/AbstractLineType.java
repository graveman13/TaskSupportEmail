package model;

public abstract class AbstractLineType {
    private String baseId;
    private String variationId;
    private String subVariationId;
    private boolean specialType;

    public AbstractLineType(String type) {
        setSpecialType(type);
        setServiceType(type);
    }

    public void setSpecialType(String type) {
        specialType = isSpecial(type);
    }

    public boolean isSpecial(String type) {
        return type.contains("*");
    }

    public String[] getType(String type) {
        return type.split("\\.");
    }

    public String getBaseId() {
        return baseId;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }

    public String getVariationId() {
        return variationId;
    }

    public void setVariationId(String variationId) {
        this.variationId = variationId;
    }

    public String getSubVariationId() {
        return subVariationId;
    }

    public void setSubVariationId(String subVariationId) {
        this.subVariationId = subVariationId;
    }

    public boolean getSpecialType() {
        return specialType;
    }

    private void setServiceType(String type) {
        String[] types = getType(type);
        for (int i = 0; i < types.length; i++) {
            if (this.baseId == null) {
                this.baseId = types[i];
            } else if (this.variationId == null) {
                this.variationId = types[i];
            } else {
                this.subVariationId = types[i];
            }
        }
    }
}
