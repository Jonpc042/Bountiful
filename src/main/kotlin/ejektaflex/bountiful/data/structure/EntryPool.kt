package ejektaflex.bountiful.data.structure

import com.google.gson.annotations.Expose
import ejektaflex.bountiful.generic.IMerge
import ejektaflex.bountiful.data.bounty.BountyEntry
import ejektaflex.bountiful.generic.IIdentifiable
import ejektaflex.bountiful.generic.ValueRegistry
import net.minecraftforge.fml.ModList

// Currently unused? Should only need a PoolRegistry and a DecreeRegistry

open class EntryPool(@Expose override val id: String) : ValueRegistry<BountyEntry>(), IMerge<EntryPool>, IIdentifiable {

    @Expose
    var modsRequired: MutableList<String>? = null

    override val canLoad: Boolean
        get() {
            return if (modsRequired == null) {
                true
            } else {
                modsRequired!!.all { ModList.get().isLoaded(it) }
            }
        }

    override fun merge(other: EntryPool) {
        content.addAll(other.content)
        modsRequired = other.modsRequired
    }


}