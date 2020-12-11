import com.thesame.baselibrary.utlis.launchstarter.task.Task
import com.thesame.baselibrary.utlis.toast.FastManager
import com.whz.base.common.BaseApplication

/**
 *Created by whz  on 2019-06-28
 */
class InitFastManageTask: Task() {
    override fun run() {
        FastManager.init(BaseApplication.myApplication)
    }
}