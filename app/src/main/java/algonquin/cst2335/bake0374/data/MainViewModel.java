package algonquin.cst2335.bake0374.data;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel{
    public MutableLiveData<Boolean> isSelected = new MutableLiveData<>();
    public String editString;
}
