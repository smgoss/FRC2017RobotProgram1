package org.usfirst.frc.team3609.robot;

import edu.wpi.first.wpilibj.SampleRobot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import com.ctre.CANTalon;
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
	// Left side motor controllers
	CANTalon mControlLeftF = new CANTalon (0);
	CANTalon mControlLeftR = new CANTalon (1);
	// Right side motor controllers
	CANTalon mControlRightF = new CANTalon (2);
	CANTalon mControlRightR = new CANTalon (4);
	// Robot drive with 4 motor controllers
	RobotDrive myRobot = new RobotDrive(mControlLeftF, mControlLeftR, mControlLeftF, mControlLeftR);
	
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
	}

	// *******************************************************************
	// robotInit - Robot wide initialization code. Required to connect to
	//             the field.
	// *******************************************************************
	@Override
	public void robotInit() {
		CameraServer.getInstance().startAutomaticCapture();
	}
	
	// *******************************************************************
	// autonomous - Only called once to run the autonomous mode. Example
	//              uses a case statement and a sendable chooser to get
	//              which automomous routine was selected in the smart
	//              dash board. Example in SampleRobot example.
	// *******************************************************************
	@Override
	public void autonomous() {
		// Nothing written for this yet.
	}

	// *******************************************************************
	//  operatorControl - Called once to start Operator control mode
	// *******************************************************************
	public void operatorControl() {
		myRobot.setSafetyEnabled(true);
		// Stay in Tele-op mode
		while (isOperatorControl() && isEnabled()) {
			// Execute a drive move
			myRobot.tankDrive(driverController.getY(Hand.kLeft), driverController.getY(Hand.kRight), true);
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
