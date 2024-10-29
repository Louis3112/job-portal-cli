

                    System.out.println("Press enter to continue");
                    sc.nextLine();
                }
                break;

                case 5:{
                    // Bingung pake reviewResume atau reviewJobApplicant
                    System.out.println("Press enter to continue");
                    sc.nextLine();
                }
                break;

                case 6 : {
                    System.out.println();
                    System.out.println("Are you sure you want to log out? (y/n)");
                    System.out.print("> ");

                    String logout = sc.nextLine();
                    if(logout.equalsIgnoreCase("y")){
                        loggedIn = false;
                    }
                    else{
                        break;