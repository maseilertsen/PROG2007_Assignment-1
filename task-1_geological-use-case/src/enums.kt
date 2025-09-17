/**
 * Allowed colors of [Mineral]
 * @property displayName
 */
enum class Color (val displayName: String) {
    PURPLE("Purple"),
    BLUE("Blue"),
    RED("Red"),
    WHITEYELLOW("White/Yellow"),
    WHITEGREEN("White/Green"),
    VARYING("Varying"),
    COLORLESS("Colorless")
}

/**
 * Allowed lusters of [Mineral]
 * @property displayName
 */
enum class Luster (val displayName: String) {
    VITREOUSGLASSY("Vitreous/Glossy"),
    VITREOUSRESINOUS("Vitreous/Resinous"),
    VITREOUSPEARLY("Vitreous/Pearly"),
    ADAMINTINE("Adamintine"),
    VITREOUSPEARLYSILKY("Vitreous/Pearly/Silky"),
    METALLIC("Metallic"),
    PEARLYGREASY("Pearly/Greasy"),
    VITREOUS("Vitreous")
}

/**
 * Allowed fractures for [Mineral]
 * @property displayName
 */
enum class Fracture {
    CONCHOIDAL,
    SPLINTERY,
    UNEVEN,
    FIBROUS,
    HACKLY,
    SUBCONCHOIDAL;

    // No need for custom names.
    val displayName: String
    get() = name.lowercase().replaceFirstChar { it.uppercase() }
}

/**
 * Identification status of an observation
 * @property displayName
 */
enum class IdentificationStatus {
    CERTAIN,
    UNSURE,
    UNDEFINED;

    val displayName: String
    get() = name.lowercase().replaceFirstChar { it.uppercase() }
}