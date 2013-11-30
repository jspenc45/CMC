package com.example.cubicmetercommunity.app;

import java.util.List;
import com.example.cubicmetercommunity.classes.Group;
import com.example.cubicmetercommunity.dbutil.SQLWork;
import com.example.cubicmetercommunityapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class NewGroupFragment extends Fragment implements OnClickListener {
	OnButtonClick buttonClick;
	List<Group> groups;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		buttonClick = (OnButtonClick) activity;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		((Button) getActivity().findViewById(R.id.sg_back))
				.setOnClickListener(this);
		((Button) getActivity().findViewById(R.id.sg_next))
				.setOnClickListener(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_new_group, container, false);
	}
	public boolean createGroup() {
		EditText txt = (EditText) getActivity().findViewById(R.id.numBeetles);
		String groupName = txt.getText().toString();
		
		if (!groupName.equals("")) {
			RoleActivity r = (RoleActivity) getActivity();
			r.setGroup(SQLWork.createNewGroup(groupName));
			return true;
		}
		
		return false;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.sg_back:
			buttonClick.popStack(this);
			break;
		case R.id.sg_next:
			if (createGroup())
				buttonClick.LoadNextFragment(new SelectRoleFragment());
			break;
		}
	}
}
