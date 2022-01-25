################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../src/Imageomatic.c \
../src/Imageomatic0.c \
../src/lodepng.c 

OBJS += \
./src/Imageomatic.o \
./src/Imageomatic0.o \
./src/lodepng.o 

C_DEPS += \
./src/Imageomatic.d \
./src/Imageomatic0.d \
./src/lodepng.d 


# Each subdirectory must supply rules for building sources it contributes
src/%.o: ../src/%.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -std=c11 -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


