package com.app.landscapeasynctask;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MedicineListAdapter extends ArrayAdapter<Medicine>{

	Context mContext;
	LayoutInflater inflater;
	int layoutResourceId;
	List<Medicine> dataMedicine;
	
	//ProductSearchFilter ProductsFilter;

	public MedicineListAdapter(Context context, int layoutResourceId, List<Medicine> Products) {
		super(context, layoutResourceId, Products);
		dataMedicine = Products;
		this.layoutResourceId = layoutResourceId;
		this.mContext = context;

	}
	
	@Override
	public int getCount() {
		return dataMedicine.size();
	}

	@Override
	public long getItemId(int position) {
		//return Long.valueOf(dataMedicine.get(position).getId());
		return 0;
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables
		View row = null;
		ViewHolder holder = null;
		Medicine medicinedata = dataMedicine.get(position);
		if (row == null) {
			LayoutInflater inflater = null;
				
			inflater = ((Activity) mContext).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new ViewHolder();
			holder.p_name  = (TextView) row .findViewById(R.id.p_name);

			row.setTag(holder);
		} else {   
			holder = new ViewHolder(); 
			holder = (ViewHolder) row.getTag(); 
		}

		//try {

			holder.p_name.setText(medicinedata.getName());

		/*}catch (Exception e){
			e.printStackTrace();
		}*/
		Log.d("MedicineName",medicinedata.getName());
		return row;
	}

	
	/**
	 * Return specific Medicine object by position
	 * @param position
	 * @return Medicine {@link Medicine}
	 */
	public Medicine getMedicineByPosition(int position){
		return dataMedicine.get(position);
	}
}
