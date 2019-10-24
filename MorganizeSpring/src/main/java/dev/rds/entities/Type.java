package dev.rds.entities;

public enum Type {
	
	MEMBER("member"),
	ADMIN("admin");
	
	private String type;
	
	Type(String type){
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
	
	public Type fromType(String string) {
		for (Type type :Type.values()){
            if (type.getType().equals(string)){
                return type;
            }
        }
        return null;
	}

}
