package org.usfirst.frc.team3609.robot;

import edu.wpi.first.wpilibj.SampleRobot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're
 * inexperienced, don't. Unless you know what you are doing, complex code will
 * be much more difficult under this system. Use IterativeRobot or Command-Based
 * instead if you're new.
 */


public class Robot extends SampleRobot {
	// An Xbox controller for driving
	XboxController driverController = new XboxController (0); 
	// Attack 3
	Joystick attack3 = new Joystick(1);
	// Left side motor controllers
	CANTalon mControlLeftF = new CANTalon (13);
	CANTalon mControlLeftR = new CANTalon (10);
	// Right side motor controllers
	CANTalon mControlRightF = new CANTalon (11);
	CANTalon mControlRightR = new CANTalon (12);
	// Robot drive
	RobotDrive myRobot = new RobotDrive(mControlLeftF, mControlRightF);
	// Climber motor controller
	SpeedController climber = new Talon(5);
	SpeedController ballCollector = new Talon(4);
	
	// *******************************************************************
	// Robot constructor
	// *******************************************************************
	public Robot() {
		myRobot.setExpiration(0.1);
		// Set all motor speeds to 0
		mControlLeftF.set(0);
		mControlLeftR.set(0);
		mControlRightF.set(0);
		mControlRightR.set(0);
		// Map the rear motors to follow the front motors
		mControlLeftR.changeControlMode(TalonControlMode.Follower);
		mControlLeftR.set(mControlLeftF.getDeviceID());
	    mControlRightR.changeControlMode(TalonControlMode.Follower);
	    mControlRightR.set(mControlRightF.getDeviceID());
	}

	// *******************************************************************
	// robotInit - Robot wide initialization code. Required to connect to
	//             the field.
	// *******************************************************************
	@Override
	public void robotInit() {
		CameraServer.getInstance().startAutomaticCapture(0);
		CameraServer.getInstance().startAutomaticCapture(1);

	}
	
	// *******************************************************************
	// autonomous - Only called once to run the autonomous mode. Example
	//              uses a case statement and a sendable chooser to get
	//              which automomous routine was selected in the smart
	//              dash board. Example in SampleRobot example.
	// *******************************************************************
	@Override
	public void autonomous() {
		int loopCount = 0;
		while(isAutonomous() && isEnabled())
		{
			if (loopCount < 300)
			{
				// Set climber to run at half speed
				// climber.set(0.5);
				// Run the ball collector at full speed
				//ballCollector.set(1);
				// Run drive base at 25%
				myRobot.tankDrive(-0.75, -0.73);
				// wait for a motor update time
				Timer.delay(0.005);
				// Update Counter
				loopCount ++;
			}
		}
		// Nothing written for this yet.
	}

	// *******************************************************************
	//  operatorControl - Called once to start Operator control mode
	// *******************************************************************
	public void operatorControl() {
		myRobot.setSafetyEnabled(true);
//		double left = 0;
//		double right = 0;
//		double oldLeft = 0;
//		double oldRight = 0;
//		double limit = 0.005; 
//		double zero_deadband = 0.2;
//		double leftkill = 1;
//		double rightkill = 1;
//		double leftreal = 0;
//		double rightreal = 0;
		
		// Stay in Tele-op mode
		while (isOperatorControl() && isEnabled()) {
//			// Execute a drive move
//			
//			left = driverController.getY(Hand.kLeft);
//			right = driverController.getY(Hand.kRight);
//			
//			
//			if (Math.abs(left) < zero_deadband)
//			{
//				leftkill = 1;
//			}
//				
//			if( left < 0 ){
//					if( oldLeft > 0 ){
//						leftkill = 1;
//					}else{
//						if(Math.abs(oldLeft-left) > limit)
//							left = (oldLeft - limit); //bcuz negative
//						leftkill = 0;
//					}
//				}else{
//					if( oldLeft < 0 ){
//						leftkill = 1;
//					}else{
//						if(Math.abs(oldLeft-left) > limit)
//							left = (oldLeft + limit); 
//						leftkill = 0;
//					}
//				}
//			//right drive control
//			if (Math.abs(right) < zero_deadband)
//			{
//				rightkill = 1;
//			}
//				
//			if( right < 0 ){
//					if( oldRight > 0 ){
//						rightkill = 1;
//					}else{
//						if(Math.abs(oldRight-right) > limit)
//						{
//							right = (oldRight - limit); //bcuz negative
//						 	rightkill = 0;
//						} else
//						{
//						
//						}
//					}
//				}else{
//					if( oldRight < 0 ){
//						rightkill = 1;
//					}else{
//						if(Math.abs(oldRight-right) > limit)
//							right = (oldRight + limit); 
//						rightkill = 0;
//					}
//				}
//			
//			
//			if(leftkill == 1){
//				leftreal = 0;
//			}else{
//				leftreal = left;
//			}
//			
//			if(rightkill == 1){
//				rightreal = 0;
//			}else{
//				rightreal = right;
//			}
			
			myRobot.tankDrive(driverController.getY(Hand.kRight)*0.9, 
					driverController.getY(Hand.kLeft )*0.9,
					true);
//			myRobot.tankDrive(rightreal*0.75, 
//					leftreal*0.75,
//					true);
//			oldLeft = left;
//			oldRight = right;
			
			

			// Set the climber speed to 50%
//			if (driverController.getXButton())
//				{
//				climber.set(1);
//				}
//			else
//			{
//				climber.set(0);
//			}
			climber.set(attack3.getY()*-1);	
			// Set ball collector speed to 100%
			//ballCollector.set(1);
			// wait for a motor update time
			Timer.delay(0.005); 
		}
	}
	// *******************************************************************
	// test - Runs during test mode.
	// *******************************************************************
	@Override
	public void test() {
		// Nothing written for this yet.
	}
}
