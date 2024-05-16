package kz.bakhus.api.models.catalog

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CatalogDataDTO(
    @SerialName("all_items_count")
    val allItemsCount: Int,
    @SerialName("header")
    val header: String,
    @SerialName("index")
    val index: Boolean,
    @SerialName("items")
    val items: List<ItemDTO>,
    @SerialName("page_items_count")
    val pageItemsCount: Int,
    @SerialName("page_number")
    val pageNumber: Int,
    @SerialName("section_1c_code")
    val section1cCode: String,
    @SerialName("section_description")
    val sectionDescription: String,
    @SerialName("section_id")
    val sectionId: Int,
    @SerialName("section_list")
    val sectionList: List<SectionDTO>
)