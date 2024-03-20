package my.foodon.app.customerFoodPanel;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

import my.foodon.app.R;
import my.foodon.app.chefFoodPanel.UpdateDishModel;

public class CustomerHomeAdapter extends RecyclerView.Adapter<CustomerHomeAdapter.ViewHolder> {


    private Context mcontext;
    private List<UpdateDishModel>updateDishModellist;
    DatabaseReference databaseReference;

    public CustomerHomeAdapter(Context context,List<UpdateDishModel>updateDishModellist)
    {
        this.updateDishModellist=updateDishModellist;
        this.mcontext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.customer_menudish,parent,false);
        return new CustomerHomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final UpdateDishModel updateDishModel=updateDishModellist.get(position);
        Glide.with(mcontext).load(updateDishModel.getImageURL()).into((ImageView) holder.itemView);
        holder.Dishname.setText(updateDishModel.getDishes());
        updateDishModel.getRandomUID();
        updateDishModel.getChefId();
        holder.price.setText("Price: ₹ " + updateDishModel.getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mcontext,OrderDish.class);
                intent.putExtra("FoodMenu",updateDishModel.getRandomUID());
                intent.putExtra("ChefId",updateDishModel.getChefId());


                mcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return updateDishModellist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView Dishname, price, Totalrs;
        TextView additem;
        TextInputLayout qty;
        String quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView=itemView.findViewById(R.id.menu_image);
            Dishname=itemView.findViewById(R.id.dishname);
            price=itemView.findViewById(R.id.dishprice);
            //additem=itemView.findViewById(R.id.number_btn);
            qty = (TextInputLayout) itemView.findViewById(R.id.Quantity);



        }
    }
}
