import java.io.IOException;

import com.phidget22.*;

public class Phidgets_WheeledRobot {

	public static double HorizontalAxisValue = 0.0;
	public static double VerticalAxisValue = 0.0;
	
    

    //Listener for joy stick
    public static VoltageRatioInputVoltageRatioChangeListener VoltageRatioHorizontalAxis = new VoltageRatioInputVoltageRatioChangeListener() {
    
    	@Override
	    public void onVoltageRatioChange(VoltageRatioInputVoltageRatioChangeEvent ev) {
    		
    		HorizontalAxisValue = ev.getVoltageRatio();
    	}
    	
    	
    };

    public static VoltageRatioInputVoltageRatioChangeListener VoltageRatioVerticalAxis = new VoltageRatioInputVoltageRatioChangeListener() {
        
    	@Override
	    public void onVoltageRatioChange(VoltageRatioInputVoltageRatioChangeEvent ev) {
    		
    		VerticalAxisValue = ev.getVoltageRatio();
    	}
    	
    	
    };

    
    
    
    //public static void addDistanceChangeListener(DistanceSensorDistanceChangeListener handler) throws PhidgetExceptio 

    
    


	public static void main(String[] args) throws PhidgetException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		boolean programIsRunning = true;
		
		Net.addServer("phidgetsbc", "192.168.99.1", 5661, "", 0);
		
		        //instantiate objects
				DCMotor LeftPair = new DCMotor();
				DCMotor RightPair = new DCMotor();
				
				VoltageRatioInput HorizontalAxis = new VoltageRatioInput();
				VoltageRatioInput VerticalAxis = new VoltageRatioInput();
				
				DistanceSensor DistanceSensor = new DistanceSensor();
				
				//set motor ports
				LeftPair.setHubPort(0);
				LeftPair.setIsHubPortDevice(false);
				
				RightPair.setHubPort(1);
				RightPair.setIsHubPortDevice(false);
				
				VerticalAxis.setHubPort(3);
				VerticalAxis.setChannel(0);
				VerticalAxis.setIsHubPortDevice(false);
				VerticalAxis.addVoltageRatioChangeListener(VoltageRatioVerticalAxis);
				
				HorizontalAxis.setHubPort(3);
				HorizontalAxis.setChannel(1);
				HorizontalAxis.setIsHubPortDevice(false);
				HorizontalAxis.addVoltageRatioChangeListener(VoltageRatioHorizontalAxis);
				
				DistanceSensor.setHubPort(2);
				DistanceSensor.setIsHubPortDevice(false);
				
				
				
				LeftPair.open(1000);
				RightPair.open(1000);
				
				
				HorizontalAxis.open(1000);
				VerticalAxis.open(1000);

				DistanceSensor.open(1000);
				
				
			//	HorizontalAxis.setVoltageRatioChangeTrigger(100);
			//	VerticalAxis.setVoltageRatioChangeTrigger(100);
				

				 Thread.sleep(300);
				
				 
				 
				 while (programIsRunning) {
					
					int distance = DistanceSensor.getDistance();
					
					
					//distance too close
					if(distance < 100)
					{
						
						System.out.println(distance);
						LeftPair.setTargetVelocity(-1);
						RightPair.setTargetVelocity(-1);
						  
						LeftPair.setTargetVelocity(0.5);
						System.out.println("Turn Around");
					}
					
					
					
				else{
					//move forward
					if(VerticalAxisValue > 0.3)
					{
					  LeftPair.setTargetVelocity(1);
					  RightPair.setTargetVelocity(1);
					  		  
					  System.out.println("Vertical");
					}
					//move backwards
					else if(VerticalAxisValue < -0.3)
					{
						  LeftPair.setTargetVelocity(-1);
						  RightPair.setTargetVelocity(-1);
					}
					//stop if the joystick is not used
					else
					{
						LeftPair.setTargetVelocity(0);		
						RightPair.setTargetVelocity(0);	
					}
						
					//turn right
					if(HorizontalAxisValue > 0.3)
					{
					 
					  LeftPair.setTargetVelocity(1);
					  System.out.println("Horizontal");
					}
					//turn left
					if(HorizontalAxisValue < -0.3)
					{
					  RightPair.setTargetVelocity(1);
					  System.out.println("Horizontal");
					}
				}
		
					//check if enter key has been pressed
				    if (System.in.available() > 0) {
				        System.out.println("Ending Program");
				        programIsRunning = false;
				    }
				    Thread.sleep(150);
				
				    
				    
				    
				}
				
				
				HorizontalAxis.close();
				VerticalAxis.close();
				
				
				LeftPair.close();
				RightPair.close();
				
				DistanceSensor.close();
	
	}

}
