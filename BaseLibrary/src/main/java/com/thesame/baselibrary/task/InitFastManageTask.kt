import com.thesame.baselibrary.common.BaseApplication
import com.thesame.baselibrary.utlis.launchstarter.task.Task
import com.thesame.baselibrary.utlis.toast.FastManager

/**
 *Created by whz  on 2019-06-28
 */
class InitFastManageTask: Task() {
    override fun run() {
        FastManager.init(BaseApplication.myApplication)
    }
}