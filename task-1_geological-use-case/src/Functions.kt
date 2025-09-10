/**
 * Lists all minerals and its data.
 */
fun listAllMinerals() {
    for (mineral in mockMineral) {
        // Check if max value is null
        var hardnessValue = "from ${mineral.hardness.min} to ${mineral.hardness.max}"
        if (mineral.hardness.max == null){ // Can be null when only one value is provided.
           hardnessValue = mineral.hardness.min.toString()
        }

        println("--- Mineral: ${mineral.name} ---\n"     +
                "\tLuster:".padEnd(13)   + "${mineral.luster.displayName}\n"   +
                "\tColor:".padEnd(13)    + "${mineral.color.displayName}\n"    +
                "\tHardness:".padEnd(13) + hardnessValue + "\n" +
                "\tFracture:".padEnd(13) + "${mineral.fracture.displayName}\n")
    }
}
