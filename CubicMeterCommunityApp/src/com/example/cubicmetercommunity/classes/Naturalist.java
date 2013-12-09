package com.example.cubicmetercommunity.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Naturalist implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public final static String GROUP_ID_FIELD = "group_id__c";
	public final static String COMMENTS_FIELD = "comments__c";
	public final static String SESSION_ID_FIELD = "session_id__c";
	public final static String SNAIL_FIELD = "snail__c";
	public final static String BRISTLETAIL_FIELD = "bristletail__c";
	public final static String LACEWING_FIELD = "lacewing__c";
	public final static String MAYFLY_FIELD = "mayfly__c";
	public final static String THRIP_FIELD = "thrip__c";
	public final static String PILLBUG_FIELD = "pillbug__c";
	public final static String BEETLE_FIELD = "beetle__c";
	public final static String SPIDER_FIELD = "spider__c";
	public final static String BUTTERFLY_FIELD = "butterfly__c";
	public final static String GRASSHOPPER_FIELD = "grasshopper__c";
	public final static String WORM_FIELD = "worm__c";
	public final static String SPRINGTAIL_FIELD = "springtail__c";
	public final static String LARVAE_FIELD = "larvae__c";
	public final static String WOODROACH_FIELD = "woodroach__c";
	public final static String CADISFLY_FIELD = "cadisfly__c";
	public final static String SCORPION_FIELD = "scorpion__c";
	public final static String TICK_FIELD = "tick__c";
	public final static String EARWIG_FIELD = "earwig__c";
	public final static String STONEFLY_FIELD = "stonefly__c";
	public final static String CATERPILLAR_FIELD = "caterpillar__c";
	public final static String CENTIPEDE_FIELD = "centipede__c";
	public final static String APHID_FIELD = "aphid__c";
	public final static String BOOKLICE_FIELD = "booklice__c";
	public final static String FLY_FIELD = "fly__c";
	public final static String BEE_FIELD = "bee__c";
	public final static String ANT_FIELD = "ant__c";

	String comments, group_id, session_id, snail, bristletail, lacewing,
			mayfly, thrip, pillbug, beetle, spider, butterfly, grasshopper,
			worm, springtail, larvae, woodroach, cadisfly, scorpion, tick,
			earwig, stonefly, caterpillar, centipede, aphid, booklice, fly,
			bee, ant;

	public static Map<String, Object> generateFieldsAll() {
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put(COMMENTS_FIELD, null);
		fields.put(GROUP_ID_FIELD, null);
		fields.put(SNAIL_FIELD, null);
		fields.put(BRISTLETAIL_FIELD, null);
		fields.put(LACEWING_FIELD, null);
		fields.put(MAYFLY_FIELD, null);
		fields.put(THRIP_FIELD, null);
		fields.put(PILLBUG_FIELD, null);
		fields.put(BEETLE_FIELD, null);
		fields.put(SPIDER_FIELD, null);
		fields.put(BUTTERFLY_FIELD, null);
		fields.put(GRASSHOPPER_FIELD, null);
		fields.put(WORM_FIELD, null);
		fields.put(SPRINGTAIL_FIELD, null);
		fields.put(LARVAE_FIELD, null);
		fields.put(WOODROACH_FIELD, null);
		fields.put(CADISFLY_FIELD, null);
		fields.put(SCORPION_FIELD, null);
		fields.put(TICK_FIELD, null);
		fields.put(EARWIG_FIELD, null);
		fields.put(STONEFLY_FIELD, null);
		fields.put(CATERPILLAR_FIELD, null);
		fields.put(CENTIPEDE_FIELD, null);
		fields.put(APHID_FIELD, null);
		fields.put(BOOKLICE_FIELD, null);
		fields.put(FLY_FIELD, null);
		fields.put(BEE_FIELD, null);
		fields.put(ANT_FIELD, null);
		fields.put(SESSION_ID_FIELD, null);

		return fields;
	}

	public Naturalist(String comment) {
		this.comments = comment;
	}

	public Naturalist(JSONObject json) {
		try {
			this.snail = json.getString(SNAIL_FIELD);
		} catch (JSONException e) {
			this.snail = "";
		}
		try {
			this.bristletail = json.getString(BRISTLETAIL_FIELD);
		} catch (JSONException e) {
			this.bristletail = "";
		}
		try {
			this.lacewing = json.getString(LACEWING_FIELD);
		} catch (JSONException e) {
			this.lacewing = "";
		}
		try {
			this.mayfly = json.getString(MAYFLY_FIELD);
		} catch (JSONException e) {
			this.mayfly = "";
		}
		try {
			this.thrip = json.getString(THRIP_FIELD);
		} catch (JSONException e) {
			this.thrip = "";
		}
		try {
			this.pillbug = json.getString(PILLBUG_FIELD);
		} catch (JSONException e) {
			this.pillbug = "";
		}
		try {
			this.beetle = json.getString(BEETLE_FIELD);
		} catch (JSONException e) {
			this.beetle = "";
		}
		try {
			this.spider = json.getString(SPIDER_FIELD);
		} catch (JSONException e) {
			this.spider = "";
		}
		try {
			this.butterfly = json.getString(BUTTERFLY_FIELD);
		} catch (JSONException e) {
			this.butterfly = "";
		}
		try {
			this.grasshopper = json.getString(GRASSHOPPER_FIELD);
		} catch (JSONException e) {
			this.grasshopper = "";
		}
		try {
			this.worm = json.getString(WORM_FIELD);
		} catch (JSONException e) {
			this.worm = "";
		}
		try {
			this.springtail = json.getString(SPRINGTAIL_FIELD);
		} catch (JSONException e) {
			this.springtail = "";
		}
		try {
			this.larvae = json.getString(LARVAE_FIELD);
		} catch (JSONException e) {
			this.larvae = "";
		}
		try {
			this.woodroach = json.getString(WOODROACH_FIELD);
		} catch (JSONException e) {
			this.woodroach = "";
		}
		try {
			this.cadisfly = json.getString(CADISFLY_FIELD);
		} catch (JSONException e) {
			this.cadisfly = "";
		}
		try {
			this.scorpion = json.getString(SCORPION_FIELD);
		} catch (JSONException e) {
			this.scorpion = "";
		}
		try {
			this.tick = json.getString(TICK_FIELD);
		} catch (JSONException e) {
			this.tick = "";
		}
		try {
			this.earwig = json.getString(EARWIG_FIELD);
		} catch (JSONException e) {
			this.earwig = "";
		}
		try {
			this.stonefly = json.getString(STONEFLY_FIELD);
		} catch (JSONException e) {
			this.stonefly = "";
		}
		try {
			this.caterpillar = json.getString(CATERPILLAR_FIELD);
		} catch (JSONException e) {
			this.caterpillar = "";
		}
		try {
			this.centipede = json.getString(CENTIPEDE_FIELD);
		} catch (JSONException e) {
			this.centipede = "";
		}
		try {
			this.aphid = json.getString(APHID_FIELD);
		} catch (JSONException e) {
			this.aphid = "";
		}
		try {
			this.booklice = json.getString(BOOKLICE_FIELD);
		} catch (JSONException e) {
			this.booklice = "";
		}
		try {
			this.fly = json.getString(FLY_FIELD);
		} catch (JSONException e) {
			this.fly = "";
		}
		try {
			this.bee = json.getString(BEE_FIELD);
		} catch (JSONException e) {
			this.bee = "";
		}
		try {
			this.ant = json.getString(ANT_FIELD);
		} catch (JSONException e) {
			this.ant = "";
		}
		try {
			this.comments = json.getString(COMMENTS_FIELD);
		} catch (JSONException e) {
			this.comments = "";
		}
		try {
			this.group_id = json.getString(GROUP_ID_FIELD);
		} catch (JSONException e) {
			this.group_id = "";
		}
		try {
			this.session_id = json.getString(SESSION_ID_FIELD);
		} catch (JSONException e) {
			this.session_id = "";
		}
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public static List<Naturalist> makeList(JSONObject obj) {
		JSONArray records = null;
		try {
			records = obj.getJSONArray("records");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		List<Naturalist> list = new ArrayList<Naturalist>();
		for (int i = 0; i < records.length(); i++) {
			Naturalist met = null;
			try {
				met = new Naturalist(records.getJSONObject(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			list.add(met);
		}
		return list;

	}
	public String getSnail() {
		return snail;
	}

	public void setSnail(String snail) {
		this.snail = snail;
	}

	public String getBristletail() {
		return bristletail;
	}

	public void setBristletail(String bristletail) {
		this.bristletail = bristletail;
	}

	public String getLacewing() {
		return lacewing;
	}

	public void setLacewing(String lacewing) {
		this.lacewing = lacewing;
	}

	public String getMayfly() {
		return mayfly;
	}

	public void setMayfly(String mayfly) {
		this.mayfly = mayfly;
	}

	public String getThrip() {
		return thrip;
	}

	public void setThrip(String thrip) {
		this.thrip = thrip;
	}

	public String getPillbug() {
		return pillbug;
	}

	public void setPillbug(String pillbug) {
		this.pillbug = pillbug;
	}

	public String getBeetle() {
		return beetle;
	}

	public void setBeetle(String beetle) {
		this.beetle = beetle;
	}

	public String getSpider() {
		return spider;
	}

	public void setSpider(String spider) {
		this.spider = spider;
	}

	public String getButterfly() {
		return butterfly;
	}

	public void setButterfly(String butterfly) {
		this.butterfly = butterfly;
	}

	public String getGrasshopper() {
		return grasshopper;
	}

	public void setGrasshopper(String grasshopper) {
		this.grasshopper = grasshopper;
	}

	public String getWorm() {
		return worm;
	}

	public void setWorm(String worm) {
		this.worm = worm;
	}

	public String getSpringtail() {
		return springtail;
	}

	public void setSpringtail(String springtail) {
		this.springtail = springtail;
	}

	public String getLarvae() {
		return larvae;
	}

	public void setLarvae(String larvae) {
		this.larvae = larvae;
	}

	public String getWoodroach() {
		return woodroach;
	}

	public void setWoodroach(String woodroach) {
		this.woodroach = woodroach;
	}

	public String getCadisfly() {
		return cadisfly;
	}

	public void setCadisfly(String cadisfly) {
		this.cadisfly = cadisfly;
	}

	public String getScorpion() {
		return scorpion;
	}

	public void setScorpion(String scorpion) {
		this.scorpion = scorpion;
	}

	public String getTick() {
		return tick;
	}

	public void setTick(String tick) {
		this.tick = tick;
	}

	public String getEarwig() {
		return earwig;
	}

	public void setEarwig(String earwig) {
		this.earwig = earwig;
	}

	public String getStonefly() {
		return stonefly;
	}

	public void setStonefly(String stonefly) {
		this.stonefly = stonefly;
	}

	public String getCaterpillar() {
		return caterpillar;
	}

	public void setCaterpillar(String caterpillar) {
		this.caterpillar = caterpillar;
	}

	public String getCentipede() {
		return centipede;
	}

	public void setCentipede(String centipede) {
		this.centipede = centipede;
	}

	public String getAphid() {
		return aphid;
	}

	public void setAphid(String aphid) {
		this.aphid = aphid;
	}

	public String getBooklice() {
		return booklice;
	}

	public void setBooklice(String booklice) {
		this.booklice = booklice;
	}

	public String getFly() {
		return fly;
	}

	public void setFly(String fly) {
		this.fly = fly;
	}

	public String getBee() {
		return bee;
	}

	public void setBee(String bee) {
		this.bee = bee;
	}

	public String getAnt() {
		return ant;
	}

	public void setAnt(String ant) {
		this.ant = ant;
	}
	public static String getGroupIdField() {
		return GROUP_ID_FIELD;
	}
	public static List<String> returnFieldList() {
		String[] fieldList = { Naturalist.SNAIL_FIELD,
				Naturalist.BRISTLETAIL_FIELD, Naturalist.LACEWING_FIELD,
				Naturalist.MAYFLY_FIELD, Naturalist.THRIP_FIELD,
				Naturalist.PILLBUG_FIELD, Naturalist.BEETLE_FIELD,
				Naturalist.SPIDER_FIELD, Naturalist.BUTTERFLY_FIELD,
				Naturalist.GRASSHOPPER_FIELD, Naturalist.WORM_FIELD,
				Naturalist.SPRINGTAIL_FIELD, Naturalist.LARVAE_FIELD,
				Naturalist.WOODROACH_FIELD, Naturalist.CADISFLY_FIELD,
				Naturalist.SCORPION_FIELD, Naturalist.TICK_FIELD,
				Naturalist.EARWIG_FIELD, Naturalist.STONEFLY_FIELD,
				Naturalist.CATERPILLAR_FIELD, Naturalist.CENTIPEDE_FIELD,
				Naturalist.APHID_FIELD, Naturalist.BOOKLICE_FIELD,
				Naturalist.FLY_FIELD, Naturalist.BEE_FIELD, Naturalist.ANT_FIELD };
		return Arrays.asList(fieldList);
	}
}
