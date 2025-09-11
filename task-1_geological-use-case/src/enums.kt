enum class Color (val displayName: String) {
    PURPLE("Purple"),
    BLUE("Blue"),
    RED("Red"),
    WHITEYELLOW("White/Yellow"),
    WHITEGREEN("White/Green"),
    VARYING("Varying"),
    COLORLESS("Colorless")
}

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

enum class IdentidicationStatus {
    CERTAIN,
    UNSURE,
    UNDEFINED;

    val displayName: String
    get() = name.lowercase().replaceFirstChar { it.uppercase() }
}