import com.thesame.baselibrary.utlis.helper.LogUtil
import com.thesame.baselibrary.utlis.launchstarter.task.Task
/**
 *Created by whz  on 2019-06-28
 */
class InitLogTask: Task() {
    override fun run() {
        LogUtil.init(true, "whz")
    }
}