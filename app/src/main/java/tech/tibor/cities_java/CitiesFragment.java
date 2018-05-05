package tech.tibor.cities_java;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CitiesFragment extends Fragment {

    private CitiesRecyclerViewAdapter mListAdapter;

    private OnListFragmentInteractionListener mListener;

    private RecyclerView mList;

    private ProgressBar mSpinner;

    private Boolean showSpinner = true;

    private void setShowSpinner(Boolean show) {
        mSpinner.setVisibility(show ? View.VISIBLE : View.GONE);
        mList.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CitiesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CitiesViewModel viewModel = ViewModelProviders.of(getActivity()).get(CitiesViewModel.class);
        viewModel.getCities().observe(this, cities -> {
            if (cities != null && mListAdapter != null) {
                mListAdapter.setValues(cities);
                mListAdapter.notifyDataSetChanged();
            }
        });

        viewModel.getLoading().observe(this, loading -> {
            if (loading != null) {
                setShowSpinner(loading);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cities_list, container, false);

        mList = view.findViewById(R.id.list);
        Context context = view.getContext();
        mList.setLayoutManager(new LinearLayoutManager(context));
        mListAdapter = new CitiesRecyclerViewAdapter(new ArrayList<>(), mListener);
        mList.setAdapter(mListAdapter);

        mSpinner = view.findViewById(R.id.progressBar);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(City item);
    }
}
