package org.usfirst.frc.team3609.robot;

import edu.wpi.first.wpilibj.SampleRobot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
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
	
												// operations
	XboxController driverController = new XboxController(0); // set to ID 1 in DriverStation
	CANTalon mControlLeftF = new CANTalon(0);
	CANTalon mControlRightF = new CANTalon(2);
	CANTalon mControlLeftR = new CANTalon(1);
	CANTalon mControlRightR = new CANTalon(4);
	RobotDrive myRobot = new RobotDrive(mControlLeftF, mControlLeftR, mControlLeftF, mControlLeftR); // class that handles basic drive
	public Robot() {
		//myRobot.setExpiration(1);
		mControlLeftF.set(0);
		mControlLeftR.set(0);
		mControlRightF.set(0);
		mControlRightR.set(0);
	}

	@Override
	public void robotInit() {
		CameraServer.getInstance().startAutomaticCapture();
	}
	/**
	 * Runs the motors with tank steering.
	 */
	
	public void operatorControl() {
		myRobot.setSafetyEnabled(false);
		while (isOperatorControl() && isEnabled()) {
			myRobot.tankDrive(driverController.getY(Hand.kLeft), driverController.getY(Hand.kRight), true);
			Timer.delay(0.005); // wait for a motor update time
		}
	}
}
