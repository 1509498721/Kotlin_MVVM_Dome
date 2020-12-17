import com.thesame.baselibrary.utlis.helper.ActivityStackManager
import com.thesame.baselibrary.common.BaseApplication
import com.thesame.baselibrary.utlis.launchstarter.task.Task
/**
 *Created by whz  on 2019-06-28
 */
class InitActivityManagerTask: Task() {
    override fun run() {
        ActivityStackManager.init(BaseApplication.myApplication)
    }
}