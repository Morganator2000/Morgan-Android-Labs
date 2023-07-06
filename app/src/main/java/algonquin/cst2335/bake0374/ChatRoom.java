package algonquin.cst2335.bake0374;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import algonquin.cst2335.bake0374.data.ChatMessage;
import algonquin.cst2335.bake0374.data.ChatRoomViewModel;
import algonquin.cst2335.bake0374.databinding.ActivityChatRoomBinding;

public class ChatRoom extends AppCompatActivity {

    ArrayList<ChatMessage> messages;

    ActivityChatRoomBinding binding;
    RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChatRoomViewModel chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Initialize to the ViewModel arraylist
        messages = chatModel.messages;

        binding.submit.setOnClickListener(click -> {
            String typed = binding.message.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateAndTime = sdf.format(new Date());
            ChatMessage typedMessage = new ChatMessage(typed, currentDateAndTime, true);
            messages.add(typedMessage);

            //Notify the adapter
            myAdapter.notifyItemInserted(messages.size()-1); //redraw missing row

            binding.message.setText(""); //removed what was typed
        });

        binding.receive.setOnClickListener(click -> {
            String typed = binding.message.getText().toString();
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
            String currentDateAndTime = sdf.format(new Date());
            ChatMessage typedMessage = new ChatMessage(typed, currentDateAndTime, false);
            messages.add(typedMessage);


            //Notify the adapter
            myAdapter.notifyItemInserted(messages.size()-1); //redraw missing row

            binding.message.setText(""); //removed what was typed
        });

        binding.recycleView.setAdapter(myAdapter = new RecyclerView.Adapter<RowHolder>() {

            @NonNull
            @Override
            public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                //viewType will be either 0 or 1, determined by getItemViewType(int position)
                if (viewType == 0) {
                    SentRowBinding rowBinding = SentRowBinding.inflate(getLayoutInflater(), parent, false);

                    return new RowHolder(rowBinding.getRoot());
                } else {
                    ReceiveRowBinding rowBinding = ReceiveRowBinding.inflate(getLayoutInflater(), parent, false);
                    return new RowHolder(rowBinding.getRoot());
                }
            }

            @Override
            public void onBindViewHolder(@NonNull RowHolder holder, int position) {
            //position will be either 0 or 1, determined by getItemViewType()
                ChatMessage message = messages.get(position);

                //override the text in the rows
                holder.message.setText(message.getMessage());
                holder.time.setText(message.getTimeSent());
            }

            //the number of items
            @Override
            public int getItemCount() {
                return messages.size();
            }

            @Override
            public int getItemViewType(int position) {
                ChatMessage message = messages.get(position);
                if(message.isSentButton()) {
                    return 0;
                } else return 1;
            }
        });
        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));
    }
    //This represents 1 row
    class RowHolder extends RecyclerView.ViewHolder {

        TextView message;
        TextView time;
        public RowHolder(@NonNull View itemView) {
            super(itemView);

            message = itemView.findViewById(R.id.theMessage);
            time = itemView.findViewById(R.id.theTime);
        }
    }
}