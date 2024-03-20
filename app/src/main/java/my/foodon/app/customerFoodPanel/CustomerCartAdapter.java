package my.foodon.app.customerFoodPanel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

import my.foodon.app.R;

public class CustomerCartAdapter extends RecyclerView.Adapter<CustomerCartAdapter.ViewHolder> {

    private Context mcontext;
    private List<Cart> cartModellist;
    static int total = 0;

    public CustomerCartAdapter(Context context, List<Cart> cartModellist) {
        this.cartModellist = cartModellist;
        this.mcontext = context;
        total = 0;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.cart_placeorder, parent, false);
        return new CustomerCartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Cart cart = cartModellist.get(position);
        holder.dishname.setText(cart.getDishName());
        holder.PriceRs.setText("Price: ₹ " + cart.getPrice());
        holder.Qty.setText("× " + cart.getDishQuantity());
        holder.Totalrs.setText("Total: ₹ " + cart.getTotalprice());
        total += Integer.parseInt(cart.getTotalprice());
        final int dishprice = Integer.parseInt(cart.getPrice());

        holder.incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(holder.Qty.getText().toString().trim()) + 1;
                int totalprice = num * dishprice;
                holder.Qty.setText("× " + num);
                holder.Totalrs.setText("Total: ₹ " + totalprice);
                updateCart(cart, num, totalprice);
            }
        });

        holder.decrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(holder.Qty.getText().toString().trim()) - 1;
                if (num < 1) {
                    num = 1;
                }
                int totalprice = num * dishprice;
                holder.Qty.setText("× " + num);
                holder.Totalrs.setText("Total: ₹ " + totalprice);
                updateCart(cart, num, totalprice);
            }
        });

        CustomerCartFragment.grandt.setText("Grand Total: ₹ " + total);
        FirebaseDatabase.getInstance().getReference("Cart").child("GrandTotal").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("GrandTotal").setValue(String.valueOf(total));
    }

    private void updateCart(Cart cart, int quantity, int totalprice) {
        if (quantity != 0) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("DishID", cart.getDishID());
            hashMap.put("DishName", cart.getDishName());
            hashMap.put("DishQuantity", String.valueOf(quantity));
            hashMap.put("Price", cart.getPrice());
            hashMap.put("Totalprice", String.valueOf(totalprice));
            hashMap.put("ChefId", cart.getChefId());

            FirebaseDatabase.getInstance().getReference("Cart").child("CartItems").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(cart.getDishID()).setValue(hashMap);
        } else {
            FirebaseDatabase.getInstance().getReference("Cart").child("CartItems").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(cart.getDishID()).removeValue();
        }
    }

    @Override
    public int getItemCount() {
        return cartModellist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView dishname, PriceRs, Totalrs, Qty;
        TextView incrementButton, decrementButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dishname = itemView.findViewById(R.id.Dishname);
            PriceRs = itemView.findViewById(R.id.pricers);
            Totalrs = itemView.findViewById(R.id.totalrs);
            Qty = itemView.findViewById(R.id.qty);

        }
    }
}