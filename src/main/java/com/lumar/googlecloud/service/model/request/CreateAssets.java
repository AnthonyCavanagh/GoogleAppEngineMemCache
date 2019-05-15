package com.lumar.googlecloud.service.model.request;

import java.util.ArrayList;
import java.util.List;

import com.lumar.googlecloud.service.model.data.Asset;

public class CreateAssets {

	private Long typeId;
	private List <Asset> asset = new ArrayList<Asset>();
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public List<Asset> getAsset() {
		return asset;
	}
	public void setAsset(List<Asset> asset) {
		this.asset = asset;
	}
	@Override
	public String toString() {
		return "CreateAssets [typeId=" + typeId + ", asset=" + asset + "]";
	}

}
