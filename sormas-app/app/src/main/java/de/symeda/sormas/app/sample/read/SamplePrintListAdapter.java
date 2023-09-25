package de.symeda.sormas.app.sample.read;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.symeda.sormas.app.backend.sample.PathogenTest;
import de.symeda.sormas.app.databinding.RowPrintLayoutSampleTestBinding;

public class SamplePrintListAdapter extends RecyclerView.Adapter<SamplePrintListAdapter.ViewHolder> {

    List<PathogenTest> pathogenTests;

    public SamplePrintListAdapter(List<PathogenTest> pathogenTests) {
        this.pathogenTests = pathogenTests;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowPrintLayoutSampleTestBinding binding = RowPrintLayoutSampleTestBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PathogenTest pathogenTest = pathogenTests.get(position);
        holder.binding.testedDisease.setText("Tested Disease :" + pathogenTest.getTestedDisease().name());
        holder.binding.testedResult.setText("Tested Result :" + pathogenTest.getTestResult().toString());
        holder.binding.testedType.setText("Tested Type :" + pathogenTest.getTestType().toString());
    }

    @Override
    public int getItemCount() {
        return pathogenTests.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        RowPrintLayoutSampleTestBinding binding;

        public ViewHolder(@NonNull RowPrintLayoutSampleTestBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
