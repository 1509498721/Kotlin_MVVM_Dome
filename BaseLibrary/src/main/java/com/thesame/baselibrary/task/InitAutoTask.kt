import com.thesame.baselibrary.utlis.launchstarter.task.Task
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.unit.Subunits

/**
 *Created by whz  on 2019-06-28
 */
class InitAutoTask: Task() {
    override fun run() {
        AutoSizeConfig.getInstance()
            .setBaseOnWidth(true)
            .unitsManager
            .supportSubunits = Subunits.MM
    }
}