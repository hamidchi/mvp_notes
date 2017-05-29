package ir.coursio.notes.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import ir.coursio.notes.R;
import ir.coursio.notes.model.MainModel;
import ir.coursio.notes.presenter.FoldersPresenter;
import ir.coursio.notes.presenter.MainPresenter;
import ir.coursio.notes.view.adapter.FolderAdapter;
import ir.coursio.notes.view.dialog.AddNewFolderDialog;
import ir.coursio.notes.view.fragment.FoldersFragment;

/**
 * Created by Taher on 28/05/2017.
 * Project: notes
 */

@SuppressLint("ViewConstructor")
public class MainView extends FrameLayout implements View.OnClickListener {

    ViewGroup mainLayout;
    MainPresenter presenter;
    FragmentManager fragmentManager;

    public MainView(@NonNull Activity activity) {
        super(activity);
        View view = inflate(getContext(), R.layout.activity_main, this);

        mainLayout = (ViewGroup) view.findViewById(R.id.mainLayout);
        FloatingActionButton fabAddFolder = (FloatingActionButton) view.findViewById(R.id.fabAddFolder);
        fabAddFolder.setOnClickListener(this);

        fragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
        LoaderManager loaderManager = ((FragmentActivity) activity).getSupportLoaderManager();
        FoldersFragment foldersFragment = (FoldersFragment) fragmentManager.findFragmentByTag("FoldersFragment");
        if (foldersFragment == null) {
            foldersFragment = new FoldersFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.mainLayout, foldersFragment, "CategoryFragment").commitAllowingStateLoss();
        }
        new FoldersPresenter(foldersFragment, loaderManager);
    }

    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }

    public void showMessage(String message) {
        Snackbar.make(mainLayout, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        AddNewFolderDialog dialog = new AddNewFolderDialog();
        dialog.setShowsDialog(true);
        dialog.show(fragmentManager, "NewFolderDialogFragment");

    }

}