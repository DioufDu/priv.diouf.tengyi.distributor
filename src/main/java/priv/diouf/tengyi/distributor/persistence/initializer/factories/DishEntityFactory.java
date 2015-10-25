package priv.diouf.tengyi.distributor.persistence.initializer.factories;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import priv.diouf.tengyi.distributor.persistence.models.Dish;
import priv.diouf.tengyi.distributor.persistence.repositories.DishRepository;
import priv.diouf.tengyi.distributor.persistence.repositories.PhotoRepository;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DishEntityFactory extends GeneralEntityFactory<Dish>implements TransactionalDataEntityFactory<Dish> {

	/*
	 * Fields
	 */

	private static final String[] SUIT_ARRAY_CN = new String[] { "豆豉蒸鲢鱼", "咖喱土豆鸡", "红烧肉焖萝卜", "回锅肉片", "肉糜冬瓜", "炒青菜", "豆豉海带", "黄豆芽油豆腐",
			"精选套餐", "全素套餐", "西式套餐", "三明治套餐", "大排", "糖醋排条", "特色三鲜暖锅", "小瓜腐竹", "酸辣大白菜", "炒青菜", "麻婆豆腐", "韭菜银牙肉丝", "蒜泥切白肉", "油泡烧仔鸭", "酸辣口水鱼",
			"青菜", "酱蛋", "酸豆角", "素鸡", "辣三丁", "糖醋小排", "大排", "三明治套餐", "西式套餐", "全素套餐", "精选套餐", "腐竹黄瓜", "手撕包菜", "蒜泥杭白菜", "花菜面筋肉片", "梅菜黄豆肉糜",
			"三鲜蛋饺", "文虎酱鸭", "糟溜回鱼片", "特色三鲜暖锅", };
	private static final String[] DISH_ARRAY_CN = new String[] { "新竹米粉", "新竹贡丸", "元祖润饼", "竹堑饼", "骨肉鱼丸汤", "成家肉粽", "黑猫包", "宜兰小吃", "渡小月",
			"阿茂米粉焿", "南方澳海鲜", "罗东小吃", "隆新一串心", "林场肉羹", "厦门馅饼", "厦门漆线雕", "文昌鱼", "青津果", "厦门彩塑", "香菇肉酱", "厦门药酒", "鼓浪屿馅饼", "荷叶粉蒸肉", "腌鬼子姜",
			"老鸭粉丝汤", "香煎茄饼", "自制辣干", "大盘鸡翅", "玉米猪骨", "胶东小炒", "酱卤鸭头", "椒麻鸡丝", " 爽口五花肉卷 ", "泡椒豆腐焖黄蜂鱼", " 油爆小河虾", " 栗子元肉百合糖水", " 南瓜山药牛肉粥",
			" 孜然鸡蛋饼 ", "小土豆红烧肉 ", "泰式甜辣凤爪", " 烤藕片" };
	private static final String[] RECOMMENDED_REASON_ARRAY = new String[] { "既符合大众口味又不失宴会品质", "鲜滑美好的滋味丝丝入心", "肉质嫩滑、皮爽、骨软，风味独特",
			"滋阴补肾、润肺养颜的功效，同时，对强健身体机能也很有帮助" };
	private static final String DESCRIPTION_TEMPLATE = "菜品清单：\r\n%s";
	private static final Character[] LINE_OPTIONS = new Character[] { 'A', 'B', 'C', 'D', 'E', 'F' };

	@Autowired
	protected DishRepository dishRepository;

	@Autowired
	protected PhotoRepository photoRepository;

	/*
	 * Creations
	 */

	@Override
	@Transactional
	protected void fulfill(List<Dish> allCreatedEntities) {
		for (int dayOff = -RandomUtils.nextInt(10, 15); dayOff < RandomUtils.nextInt(10, 15); dayOff++) {
			Calendar offerDate = Calendar.getInstance();
			offerDate.add(Calendar.DATE, dayOff);
			for (int idx = 0; idx < RandomUtils.nextInt(10, 50); idx++) {
				if (offerDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || offerDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
					continue;
				}
				Dish dish = new Dish();
				// Scalar Properties
				dish.setChineseName(SUIT_ARRAY_CN[RandomUtils.nextInt(0, SUIT_ARRAY_CN.length)].toString());
				StringBuilder englishName = new StringBuilder();
				dish.setEnglishName(englishName.toString());
				dish.setLine(LINE_OPTIONS[RandomUtils.nextInt(0, LINE_OPTIONS.length)].toString());
				generateDescription(dish);
				dish.setOfferDate(offerDate);
				dish.setRecommended(RandomUtils.nextInt(0, 3) == 0);
				dish.setRecommendedReason(RECOMMENDED_REASON_ARRAY[RandomUtils.nextInt(0, RECOMMENDED_REASON_ARRAY.length)]);
				Calendar createDate = Calendar.getInstance();
				int pastCreatedDateCount = RandomUtils.nextInt(0, 10);
				createDate.add(Calendar.DATE, dayOff - pastCreatedDateCount);
				dish.getModification().setCreateDateTime(createDate);
				Calendar modifiedDate = Calendar.getInstance();
				if (pastCreatedDateCount > 1) {
					modifiedDate.add(Calendar.DATE, dayOff - RandomUtils.nextInt(0, pastCreatedDateCount));
				}
				dish.getModification().setLastModifiedDateTime(modifiedDate);
				// Save
				allCreatedEntities.add(dish);
			}
		}
	}

	/*
	 * Metadata Information
	 */

	@Override
	protected EntityFactory<?>[] getDependEntityFactories() {
		return new EntityFactory<?>[0];
	}

	@Override
	public Class<Dish> getEntityType() {
		return Dish.class;
	}

	@Override
	protected JpaRepository<Dish, Long> getGeneralJpaRepository() {
		return dishRepository;
	}

	/*
	 * Private & Protected Methods
	 */

	protected void generateDescription(Dish meal) {
		// Meat Dish
		StringBuilder dishDiets = new StringBuilder();
		for (int idx = 0; idx < RandomUtils.nextInt(1, 5); idx++) {
			int mealIndex = RandomUtils.nextInt(0, DISH_ARRAY_CN.length);
			dishDiets.append(DISH_ARRAY_CN[mealIndex] + ", \r\n");
		}

		// Final Description
		meal.setDescription(String.format(DESCRIPTION_TEMPLATE, dishDiets));
	}
}
