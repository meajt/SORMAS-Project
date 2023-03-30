package de.symeda.sormas.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import de.symeda.sormas.app.backend.common.AbstractDomainObject;
import de.symeda.sormas.app.databinding.ActiviityBasePrintLayoutBinding;
import de.symeda.sormas.app.util.Consumer;

public abstract class BasePrintActivity<ActivityRootEntity extends AbstractDomainObject> extends AppCompatActivity {
    protected ActiviityBasePrintLayoutBinding binding;
    protected ActivityRootEntity storedRootEntity = null;
    private String rootUuid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActiviityBasePrintLayoutBinding.inflate(this.getLayoutInflater());
        setContentView(binding.getRoot());
        updateRootUuid();
        buildLayout();

    }

    private void updateRootUuid() {
        rootUuid = getIntent().getStringExtra("rootUuid");
    }

    protected static void startActivity(Context context, Class activityClass, String rootUuid) {
        Intent intent = new Intent(context, activityClass);
        intent.putExtra("rootUuid", rootUuid);
        context.startActivity(intent);
    }

    protected void requestRootData(final Consumer<ActivityRootEntity> callback) {

        if (rootUuid != null && !rootUuid.isEmpty()) {
            storedRootEntity = queryRootEntity(rootUuid);
        } else {
            storedRootEntity = null;
        }

        // This should not happen; however, it still might under certain circumstances
        // (user clicking a notification for a task they have no access to anymore); in
        // this case, the activity should be closed.
        if (storedRootEntity == null) {
            finish();
        }

        callback.accept(storedRootEntity);
    }

    protected abstract ActivityRootEntity queryRootEntity(String recordUuid);
    protected abstract void buildLayout();
}
